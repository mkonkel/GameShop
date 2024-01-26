package features.users

import features.Component

interface UsersListComponent : Component<UsersModel> {
    fun onEdit()

    fun onDelete()
}
