package com.example.formasterclass

enum class Screens {
    LIST_REPOSITORY,
    REPOSITORY_DETAILS;

    override fun toString(): String {
       return when (this) {
           LIST_REPOSITORY -> "listOfRepositoryScreen"
           REPOSITORY_DETAILS -> "repositoryDetails"
        }
    }
}
