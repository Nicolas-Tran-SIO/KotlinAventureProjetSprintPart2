package service
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
class GPTService {
    val apiKey = "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"
    fun fetchHistoire(prompt:String){
        val gpt3Endpoint = "https://api.openai.com/v1/engines/davinci/completions"

        val client = OkHttpClient()

        val jsonMediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = """
        {
            "prompt": "$prompt",
            "max_tokens": 50
        }
    """.trimIndent().toRequestBody(jsonMediaType)

        val request = Request.Builder()
            .url(gpt3Endpoint)
            .addHeader("Authorization", "Bearer $apiKey")
            .post(requestBody)
            .build()

        val response = client.newCall(request).execute()
        val responseBody = response.body?.string()

        if (response.isSuccessful && responseBody != null) {
            println("Réponse de l'API GPT-3 : $responseBody")
        } else {
            println("La requête a échoué.")
        }
    }
}