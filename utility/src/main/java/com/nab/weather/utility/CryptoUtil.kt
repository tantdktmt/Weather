package com.nab.weather.utility

import android.content.Context
import android.os.Build
import android.security.KeyPairGeneratorSpec
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import com.nab.weather.utility.sharedpref.SharedPreferenceUtil
import dagger.hilt.android.qualifiers.ApplicationContext
import java.math.BigInteger
import java.security.InvalidAlgorithmParameterException
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PrivateKey
import java.security.PublicKey
import java.util.Calendar
import java.util.Locale
import javax.crypto.Cipher
import javax.crypto.Cipher.getInstance
import javax.inject.Inject
import javax.inject.Singleton
import javax.security.auth.x500.X500Principal

@Singleton
class CryptoUtil @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {

        private const val CA = "CN=TrueMoney, O=TrueMoneyOrganization"
        private const val UTF_8 = "UTF-8"
        private const val ANDROID_KEY_STORE = "AndroidKeyStore"
        private const val KEY_ALGORITHM_RSA = "RSA"
        private const val RSA_MODE = "RSA/ECB/PKCS1Padding"
    }

    fun saveWeatherApiAppId(appId: String) =
        encrypt(appId)?.let { SharedPreferenceUtil.saveWeatherApiAppId(it) }

    fun getWeatherApiAppId() = decrypt(SharedPreferenceUtil.getWeatherApiAppId())

    private fun encrypt(plainText: String?): String? {
        try {
            val keyStore = initKeyStore()
            if (plainText == null || keyStore == null) return null
            val publicKey = keyStore.getCertificate(context.packageName).publicKey
            val bytes = plainText.toByteArray(charset(UTF_8))
            val encryptedBytes = encryptUsingKey(publicKey, bytes)
            return Base64.encodeToString(encryptedBytes, Base64.DEFAULT)
        } catch (e: Exception) {
            LogUtil.e("encrypt error ${e.toString()}")
        }
        return null
    }

    private fun decrypt(cipherText: String?): String? {
        try {
            if (cipherText == null) return null
            val keyStore = KeyStore.getInstance(ANDROID_KEY_STORE)
            keyStore.load(null)
            val entry = keyStore.getEntry(context.packageName, null) as KeyStore.PrivateKeyEntry
            val privateKey = entry.privateKey
            val bytes = cipherText.toByteArray(charset(UTF_8))
            val base64encryptedBytes = Base64.decode(bytes, Base64.DEFAULT)
            val decryptedBytes = decryptUsingKey(privateKey, base64encryptedBytes) ?: return null
            return String(decryptedBytes)
        } catch (e: Exception) {
            LogUtil.e("decrypt error $e.stackTraceToString()")
        }
        return null
    }

    @Suppress("Deprecation")
    private fun initKeyStore(): KeyStore? {
        try {
            val keyStore = KeyStore.getInstance(ANDROID_KEY_STORE)
            keyStore.load(null)
            val containsAlias = keyStore.containsAlias(context.packageName)
            if (!containsAlias) {
                val kpg = KeyPairGenerator.getInstance(
                    KEY_ALGORITHM_RSA,
                    ANDROID_KEY_STORE
                )
                val start = Calendar.getInstance(Locale.ENGLISH)
                val end = Calendar.getInstance(Locale.ENGLISH)
                end.add(Calendar.YEAR, 1)
                val spec = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    KeyPairGeneratorSpec.Builder(context)
                        .setAlias(context.packageName)
                        .setSubject(X500Principal(CA))
                        .setSerialNumber(BigInteger.TEN)
                        .setStartDate(start.time)
                        .setEndDate(end.time)
                        .build()
                } else {
                    KeyGenParameterSpec.Builder(
                        context.packageName,
                        KeyProperties.PURPOSE_DECRYPT or KeyProperties.PURPOSE_ENCRYPT
                    )
                        .setDigests(KeyProperties.DIGEST_SHA256, KeyProperties.DIGEST_SHA512)
                        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                        .build()
                }

                try {
                    kpg.initialize(spec)
                } catch (e: InvalidAlgorithmParameterException) {
                    LogUtil.e(e.stackTraceToString())
                }
                kpg.generateKeyPair()
            }
            return keyStore
        } catch (e: Exception) {
            LogUtil.e("initKeyStore error: ${e.stackTraceToString()}")
        }
        return null
    }

    private fun encryptUsingKey(
        publicKey: PublicKey,
        bytes: ByteArray
    ): ByteArray? {
        return try {
            val inCipher = getInstance(RSA_MODE)
            inCipher.init(Cipher.ENCRYPT_MODE, publicKey)
            inCipher.doFinal(bytes)
        } catch (e: Exception) {
            LogUtil.e("encryptUsingKey error: ${e.stackTraceToString()}")
            null
        }
    }

    private fun decryptUsingKey(
        privateKey: PrivateKey,
        bytes: ByteArray
    ): ByteArray? {
        return try {
            val inCipher = getInstance(RSA_MODE)
            inCipher.init(Cipher.DECRYPT_MODE, privateKey)
            inCipher.doFinal(bytes)
        } catch (e: Exception) {
            LogUtil.e("decryptUsingKey error ${e.stackTraceToString()}")
            null
        }
    }
}
