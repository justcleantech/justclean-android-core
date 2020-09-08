# Welcome to JC-Network-Kit
Please use this guide to help you going through the kit and get familier with it's component and usage


# Table of Contents
---

1. [Components](#components)
    1. [APIService](#api)
    2. [RequestType](#type)
    3. [NetworkController](#controller)
    4. [KoinModule](#koin)
    5. [Model](#model)
    6. [RequestBody](#body)
    
2. [Usage](#usage)
    1. [ApiEndPoints](#endpoints)
    2. [Repository](#repository)
    3. [JsonDeserializer](#serializer)
    4. [ViewModel](#viewmodel)

3. [Final Thoughts](#final)
    1. [PROS & CONS](#pros)
    2. [Conclusion](#conclusion)
    3. [Future Enhancement](#future)

<a name="components"></a>
### Components

------

<a name="api"></a>
#### 1. APIService

This is the API Service Interface which contains the HTTP methods that will be used to place our requests
 ```kotlin
interface APIService {

    @GET()
    fun getRequest(@Url fullUrl: String, @QueryMap queries: Map<String, String>? = HashMap()): Flowable<JsonElement>

    @POST()
    fun postRequest(@Url fullUrl: String, @Body requestBody: RequestBody): Flowable<JsonElement>

    @PUT()
    fun putRequest(@Url fullUrl: String, @Body requestBody: RequestBody): Flowable<JsonElement>

    @PATCH()
    fun patchRequest(@Url fullUrl: String, @Body requestBody: RequestBody): Flowable<JsonElement>

    @DELETE()
    fun deleteRequest(@Url fullUrl: String): Flowable<JsonElement>

}
```

------

<a name="type"></a>
#### 2. RequestType

RequestType is a sealed class used inside the NetworkController to indicate the request type and hold any possible data and as per 
[Sealed classes documentation](https://kotlinlang.org/docs/reference/sealed-classes.html)
 `The key benefit of using sealed classes comes into play when you use them in a when expression`
```kotlin
sealed class RequestType {

    data class GET(val queries: Map<String, String>? = null) : RequestType()

    data class POST(val body: RequestBody) : RequestType()

    data class PUT(val body: RequestBody) : RequestType()

    data class PATCH(val body: RequestBody) : RequestType()

    object DELETE : RequestType()
}
```

------

<a name="controller"></a>
#### 3. NetworkController

Serves as the Network-Kit gateway for the cosnumer to be able to place requests
```kotlin
object NetworkController: KoinComponent {

    private val request: APIService by inject()

    fun processRequest(type: RequestType, fullUrl: String) =
        when (type) {
            is GET -> request.getRequest(fullUrl, type.queries)
            is POST -> request.postRequest(fullUrl, type.body)
            is PUT -> request.putRequest(fullUrl, type.body)
            is PATCH -> request.patchRequest(fullUrl, type.body)
            is DELETE -> request.deleteRequest(fullUrl)
        }
}
```

------

<a name="koin"></a>
#### 4. KoinModule

Here where we will be adding all the retrofit configrations alongside with the interceptors to be injected in the NetworkController
```kotlin
val dataManagerModule = module {
    single {
        val client = OkHttpClient.Builder()
        
        client.addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        
        val retrofit = Retrofit.Builder()
            .client(client.build())
            .baseUrl("https://google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build()

        retrofit.create(APIService::class.java)
    }
}
```

------

<a name="model"></a>
#### 5. Model

Response main model
```kotlin
data class Model<Data>(
    val data: Data,
    var message: String,
    var status: Boolean,
    var code: Int
)
```

------

<a name="body"></a>
#### 5. RequestBody

Base class to be extended for the POST, PUT and PATCH methods to pass the request data 
```kotlin
open class RequestBody
```

<a name="usage"></a>
### Usage

------

<a name="endpoints"></a>
#### 1. ApiEndPoints

You need to have a place to add all your api endpoints alongside with the BASE_URL
```kotlin
object ApiEndPoints {
    
    private const val BASE_URL: String = BuildConfig.SERVER_URL + BuildConfig.SERVER_EXTENSION
    
    const val LANGUAGES = "${BASE_URL}languages"
    
}
```

------

<a name="repository"></a>
#### 2. Repository

From the Repository you can use NetworkController to place requests and return Flowable 
Also you need to map the JsonElement response to Model<Data> using JsonDeserializer
```kotlin
class ApiManagerRepository {

    fun getLanguages() = NetworkController.processRequest(RequestType.GET(), APIEndpoints.LANGUAGES)
        .map { JsonDeserializer.getLanguages(it) }

}
```

------

<a name="serializer"></a>
#### 3. JsonDeserializer

This serializer is responsible for getting Model<> of your response type 
```kotlin
object JsonDeserializer {

    fun getLanguages(json: JsonElement): Model<List<Language>> {
        val type: Type = object : TypeToken<Model<List<Language>>>() {}.type
        return Gson().fromJson<Model<List<Language>>>(json, type)
    }
    
}
```


------

<a name="viewmodel"></a>
#### 4. ViewModel

This serializer is responsible for getting Model<> of your response type 
```kotlin
  repository.getLanguages()
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({
          handleResponse(it)
      }, {
          handleError(it)
      })
```

<a name="final"></a>
### Final Thoughts

------

<a name="pros"></a>
#### 1. PROS & CONS

PROS                            |  CONS                     |
------------------------------- | ------------------------- |
One line API Call               | - |
Ready to use network layer      | - |
No extra configration required  | - |
Serves any usecase              | - |
Project independant             | - |
-| Force you using Koin         |
-| Response mapping required    |

------

<a name="conclusion"></a>
#### 2. Conclusion
We can conclude the previous comparison into the following 
* If you suffer from setting up you network layer in each new project and also need to git ride of the boilerplate code to be expressive and to focus on writing your lovely code we highly recommend you to use this kit because you can get your API calls ready by (SOC) single line of code
* There is no case that you might don't need to use it 🤷‍♂️

<a name="future"></a>
#### 3. Future Enhancement
The incoming features is including but not limited to
* Ability to place MultiPart Form Data requests
* Koin-free ready to use version
* Coroutines supported version
