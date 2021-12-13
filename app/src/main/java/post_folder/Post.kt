package post_folder

import users_folder.users

data class Post(
    val text:String="",
    val createdBy: users =users(),
    val createdAt:Long=0L
)

