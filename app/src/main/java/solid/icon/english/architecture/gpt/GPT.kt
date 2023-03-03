package solid.icon.english.architecture.gpt

import android.util.Log
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class GPT {

    private val TAG = "GPT"
    private val JSON: MediaType = "application/json; charset=utf-8".toMediaType()
    var client = OkHttpClient()

    fun giveDefinition(word: String, translation: String, onSuccessGpt: OnSuccessGpt) {
        val question =
            "Without any words, give only a definition of word «$word» with meaning «$translation»"
        val jsonBody = JSONObject()
        try {
            jsonBody.put("model", "text-davinci-003")
            jsonBody.put("prompt", question)
            jsonBody.put("max_tokens", 300)
            jsonBody.put("temperature", 0)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val body = RequestBody.create(JSON, jsonBody.toString())
        val request = Request.Builder()
            .url("https://api.openai.com/v1/completions")
            .header("Authorization", "Bearer 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")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, "Failed to load response due to " + e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    try {
                        val jsonObject = JSONObject(response.body!!.string())
                        val jsonArray = jsonObject.getJSONArray("choices")
                        val result = jsonArray.getJSONObject(0).getString("text")
                        onSuccessGpt.onSuccess(result.trim { it <= ' ' })
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }
        })

    }

}