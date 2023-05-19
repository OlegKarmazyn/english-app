package solid.icon.english.navigation_menu.account.models

class UserProfileItem {
    var userName: String? = null
    var surname: String? = null
    var phone: String? = null
    var birthday: String? = null
    var email: String? = null
    var isTeacher: Boolean? = null
    var first_login: String? = null
    var budget_key: String? = null
    var chat_key: String? = null


    constructor() {}
    constructor(
        userName: String?,
        surname: String?,
        phone: String?,
        birthday: String?,
        email: String?,
        isTeacher: Boolean?,
        first_login: String?,
        budget_key: String?,
        chat_key: String?
    ) {
        this.userName = userName
        this.surname = surname
        this.phone = phone
        this.birthday = birthday
        this.email = email
        this.isTeacher = isTeacher
        this.first_login = first_login
        this.budget_key = budget_key
        this.chat_key = chat_key
    }


}