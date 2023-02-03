package com.tinkooladik.kmmplayground.entity

object TestData {
    val ROCKET_1 = Rocket(
        id = "id",
        name = "Falcon 1",
        type = "v1.0"
    )
    val ROCKET_2 = Rocket(
        id = "id",
        name = "Falcon 9",
        type = "v1.0"
    )

    val LAUNCH_1 = RocketLaunch(
        flightNumber = 0,
        missionName = "CRS-1",
        launchYear = 2023,
        launchDateUTC = "",
        rocket = ROCKET_1,
        details = LOREM,
        launchSuccess = true,
        links = Links(
            missionPatchUrl = null,
            articleUrl = null
        )
    )
    val LAUNCH_2 = RocketLaunch(
        flightNumber = 0,
        missionName = "FalconSat",
        launchYear = 2008,
        launchDateUTC = "",
        rocket = ROCKET_1,
        details = null,
        launchSuccess = true,
        links = Links(
            missionPatchUrl = null,
            articleUrl = null
        )
    )
    val LAUNCH_3 = RocketLaunch(
        flightNumber = 0,
        missionName = "Falcon 9 Test Flight",
        launchYear = 2023,
        launchDateUTC = "",
        rocket = ROCKET_2,
        details = LOREM,
        launchSuccess = true,
        links = Links(
            missionPatchUrl = null,
            articleUrl = null
        )
    )
}

private const val LOREM =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"
