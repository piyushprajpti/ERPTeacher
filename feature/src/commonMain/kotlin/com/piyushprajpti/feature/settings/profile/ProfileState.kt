package com.piyushprajpti.feature.settings.profile

data class ProfileState(
    val isLoading: Boolean = false,
    val profileData: StudentProfile? = StudentProfile(
        name = "Arjun Mehta",
        profileImage = null,
        enrollmentNo = "2024-CS-042",
        courseTag = "B.TECH CS",
        basicInfo = listOf(
            ProfileItem("DOB", "14 Aug 2004", isFullWidth = false),
            ProfileItem("GENDER", "Male", isFullWidth = false),
            ProfileItem("BLOOD GROUP", "O +ve", isFullWidth = false),
            ProfileItem("CATEGORY", "General", isFullWidth = false)
        ),
        academicDetails = listOf(
            ProfileItem("COURSE", "Bachelor of Technology (CS)", isFullWidth = true),
            ProfileItem("YEAR", "Year 2", isFullWidth = false),
            ProfileItem("SECTION", "Section A", isFullWidth = false),
            ProfileItem("ROLL NO", "42", isFullWidth = false),
            ProfileItem("BATCH", "2024 - 2028", isFullWidth = false)
        ),
        contactDetails = listOf(
            ProfileItem("PHONE", "+91 98765 43210", isFullWidth = true, icon = ProfileIcon.Phone),
            ProfileItem("EMAIL", "arjun.m@university.edu", isFullWidth = true, icon = ProfileIcon.Email),
            ProfileItem("ADDRESS", "alat 402, Skyline Apartments, Sector 15, New Delhi - 110001", isFullWidth = true, icon = ProfileIcon.Location)
        ),
        guardianDetails = listOf(
            ProfileItem("NAME", "Rajesh Mehta", isFullWidth = true),
            ProfileItem("RELATION", "Father", isFullWidth = false),
            ProfileItem("CONTACT", "+91 98700 11223", isFullWidth = false)
        )
    )
)

data class StudentProfile(
    val name: String,
    val enrollmentNo: String,
    val courseTag: String,
    val profileImage: String? = null,
    val basicInfo: List<ProfileItem>,
    val academicDetails: List<ProfileItem>,
    val contactDetails: List<ProfileItem>,
    val guardianDetails: List<ProfileItem>
)

data class ProfileItem(
    val label: String,
    val value: String,
    val isFullWidth: Boolean = false,
    val icon: ProfileIcon? = null
)

enum class ProfileIcon {
    Phone, Email, Location
}
