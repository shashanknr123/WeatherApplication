document.getElementById('getWeather').addEventListener('click', () => {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition, showError);
    } else {
        document.getElementById('error').innerHTML = "Geolocation is not supported by this browser.";
    }
});

function showPosition(position) {
    const latitude = position.coords.latitude;
    const longitude = position.coords.longitude;

    fetch(`http://localhost:8080/weather?lat=${latitude}&lon=${longitude}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('cityName').innerHTML = `<span>City</span><br>${data.cityName} <img src="location.png">`;
            document.getElementById('temperature').innerHTML = `<span>Temp</span><br>${data.temperature} °C`;
            document.getElementById('feelsLike').innerHTML = `<span>Feels Like</span><br> ${data.feelsLike} °C`;
            document.getElementById('weatherMain').innerHTML = `<span>Condition</span><br>${data.weatherMain}<img src="http://openweathermap.org/img/wn/${data.weatherIcon}.png">`;
            document.getElementById('weatherDescription').innerHTML = `<span>Description</span><br>${data.weatherDescription}`;
            document.getElementById('humidity').innerHTML = `<span>Humidity</span><br> ${data.humidity}%`;
            document.getElementById('pressure').innerHTML = `<span>Pressure</span><br> ${data.pressure} hPa`;
            document.getElementById('visibility').innerHTML = `<span>Visibility</span><br> ${data.visibility/1000} km`;
            document.getElementById('cloudiness').innerHTML = `<span>Cloudiness</span><br> ${data.cloudiness}%`;
            document.getElementById('tempMax').innerHTML = `<span>Max Temp</span><br> ${data.tempMax} °C`;
            
        })
        .catch(error => {
            document.getElementById('error').innerHTML = "Error fetching weather data.";
            console.error('Error:', error);
        });
}

function showError(error) {
    switch(error.code) {
        case error.PERMISSION_DENIED:
            document.getElementById('error').innerHTML = "User denied the request for Geolocation.";
            break;
        case error.POSITION_UNAVAILABLE:
            document.getElementById('error').innerHTML = "Location information is unavailable.";
            break;
        case error.TIMEOUT:
            document.getElementById('error').innerHTML = "The request to get user location timed out.";
            break;
        case error.UNKNOWN_ERROR:
            document.getElementById('error').innerHTML = "An unknown error occurred.";
            break;
    }
}
