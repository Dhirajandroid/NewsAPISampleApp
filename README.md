# NewsAPISampleApp

This is a sample app based on News API: https://newsapi.org/docs/endpoints/top-headlines.

Here, I have used latest concepts like Dagger 2, RxJava, MVVM, Livedata, Room Libarary etc.

The app contains a single activity which is intially fetching all the headlines data, and storing in SqliteDatabase using Room. Then, I have implemented infinite scrolling mechanism, and thus showing results as per a limit and offset. 

For API calls, Retrofit has been used, Glide is used for showing images and Moshi Libarary. 
