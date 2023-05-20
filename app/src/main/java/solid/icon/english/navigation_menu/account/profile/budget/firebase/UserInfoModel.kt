package solid.icon.english.navigation_menu.account.profile.budget.firebase

class UserInfoModel {
    var userName: String? = null
    var paidLessons: Int? = null

    constructor() {}
    constructor(userName: String?, paidLessons: Int?) {
        this.userName = userName
        this.paidLessons = paidLessons
    }
}