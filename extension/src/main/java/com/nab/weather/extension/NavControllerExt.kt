package com.nab.weather.extension

import android.net.Uri
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

/**
 * Jetpack Navigation's deeplink navigate in safety way
 */
fun NavController.safeDeepLinkNavigate(uri: Uri, navOptions: NavOptions? = null) {
    if (graph.hasDeepLink(uri)) {
        navigate(uri, navOptions)
    }
}

fun NavController.safeNavigate(
    @IdRes actionId: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null
) {
    currentDestination?.getAction(actionId)?.let {
        navigate(actionId, args, navOptions)
    }
}

fun NavController.safeNavigate(
    navDirections: NavDirections,
    navOptions: NavOptions? = null
) {
    currentDestination?.getAction(navDirections.actionId)?.let {
        navigate(navDirections, navOptions)
    }
}

fun NavController.safeNavigate(
    navDirections: NavDirections,
    navigatorExtras: Navigator.Extras
) {
    currentDestination?.getAction(navDirections.actionId)?.let {
        navigate(navDirections, navigatorExtras)
    }
}
