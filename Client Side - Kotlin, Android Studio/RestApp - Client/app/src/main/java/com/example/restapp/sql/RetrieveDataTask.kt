package com.example.restapp.sql

import android.os.AsyncTask
import android.util.Log
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.net.Socket

class RetrieveDataTask : AsyncTask<String, Void, String>()  {
    private val exception: Exception? = null
    private var modifiedSentence: String? = null

    override fun doInBackground(vararg p0: String?): String {
        var clientSocket: Socket? = null

        try {

            clientSocket = Socket("10.100.102.7", 10000)
            val outToServer = DataOutputStream(clientSocket.getOutputStream())

            val inFromServer = BufferedReader(InputStreamReader(clientSocket.getInputStream()))

            Log.d("result", "yes")

            Log.d("outcome", "outcome is : " + p0[0] + " - " + p0[1])
            if (p0[1].equals("")){
                outToServer.writeBytes(p0[0] + '\n') // query
            } else {
                outToServer.writeBytes(p0[0] +',' + p0[1] + '\n') // query
            }
//            outToServer.writeBytes("pullUser" + '\n') // query

            modifiedSentence = inFromServer.readLine()
        } catch (e: IOException){
            Log.d("F","FAILED")
            return "failed"
        }

        if (modifiedSentence!!.isEmpty()) return "failed"

        return modifiedSentence!!

    }

//    override fun onPostExecute(feed: String?): String {
    override fun onPostExecute(feed: String?) {
        Log.d("after", feed!!)
//        return feed
    }

}
