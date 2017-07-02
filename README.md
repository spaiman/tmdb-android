# TMDB Android
The Movie Database (TMDb) Android Application 

To use the app, you need to supply your own TMDb API Key to the project before compiling the project.
Put your TMDb API Key in `$rootProject/keystore.properties` with the following format:
```
TMDB_API_KEY="<your TMDB API key>"
```

Example:
```
TMDB_API_KEY="123e51es51b713ac21bq4071273bb758"
```

For more information how it retrieves TMDb API Key, you could see the configuration in `app/build.gradle`.
