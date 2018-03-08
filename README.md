# BookViewer (Arch Componentes + Retrofit2 + Dagger2 example in Kotlin)

A few notes:
1) It's coded in Kotlin.
2) I tried to use the latest architecture guidelines proposed by google (LiveData + ViewModel) using a MVVM approach, with a repository class for handling data.
3) I used an offline-first as poposed by the Offline Cookbook by JakeArchibald.com with Room database as my cache.
4) Used Retrofit with Rx2Adapter for requests. 
5) Used Dagger2 for DI. 
6) Context is never passed from View to ViewModel. When context was needed, its encapsulated inside a Service (such as in ToastService, ResourceService, etc) and application context from appComponent is used.
