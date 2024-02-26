import axios from "axios";

// This URI is the base used for doing a geocode search using location addresses
const geoCoderUri = '/v1/geocode/search?';

// Example get request URL for getting geocode by address: https://api.geoapify.com/v1/geocode/search?text=1214-1224%20West%20Van%20Buren%20Street%2C%20Chicago%2C%20IL%2060607%2C%20United%20States%20of%20America&lang=en&limit=5&format=json&apiKey=YOUR_API_KEY


// This URI is the base used for finding the route between 2+ location coordinates
const routingUri = '/v1/routing?waypoints=';

// Example get request URL for getting route between two places: https://api.geoapify.com/v1/routing?waypoints=39.127973,-84.524567|39.1429225,-84.5090381&mode=drive&apiKey=YOUR_API_KEY


const geoapify = axios.create({
    baseURL: import.meta.env.VITE_GEOAPIFY_BASE_URL
});

function formatAddressForGeoApify(locationAddress) {
    const stateAbbreviationRegex = new RegExp('^[A-Z]{2}$', 'g');

    let splitLocationAddress = locationAddress.split(' ');
    for (let index = 0; index < splitLocationAddress.length; index++) {
        if (stateAbbreviationRegex.test(splitLocationAddress[index])) {
            splitLocationAddress[index] += ',';
        }
    }

    locationAddress = splitLocationAddress.join(' ');

    splitLocationAddress = locationAddress.split(', ');

    locationAddress = splitLocationAddress.join('%2C%20');

    splitLocationAddress = locationAddress.split(' ');

    locationAddress = splitLocationAddress.join('%20');

    return locationAddress;
}

export default {
    getRoutes(startingLocationCoordinates, landmarksArray) {

        return Promise.all(landmarksArray.map(landmark => {
            let requestUrl = (
                //import.meta.env.VITE_GEOAPIFY_BASE_URL + 
                routingUri +
                startingLocationCoordinates.startingLocationLat +
                ',' +
                startingLocationCoordinates.startingLocationLong +
                '|' + 
                landmark.landmarkLatitude +
                ',' +
                landmark.landmarkLongitude + 
                '&mode=drive' +
                '&units=imperial' +
                '&apiKey=' + import.meta.env.VITE_GEOAPIFY_API_KEY
            );

            return geoapify.get(requestUrl);
        }));

    }, 
    getItineraryRoute(waypointsCoordinatesArray) {

        let waypointsString = "";
        for (let index = 0; index < waypointsCoordinatesArray.length; index++) {
            waypointsString += waypointsCoordinatesArray[index].latitude + ',' + waypointsCoordinatesArray[index].longitude;
            if (index !== waypointsCoordinatesArray.length - 1) waypointsString += '|';
        }

        let requestURL = (
            routingUri + 
            waypointsString +
            "&mode=drive" +
            "&type=short" +
            "&details=instruction_details" +
            "&format=geojson" +
            "&apiKey=" + import.meta.env.VITE_GEOAPIFY_API_KEY
        );

        return geoapify.get(requestURL);
    },
    getCoordinatesForAddress(locationAddress) {

        locationAddress = formatAddressForGeoApify(locationAddress);

        let requestUrl = (
            geoCoderUri +
            "text=" +
            locationAddress +
            '&lang=en&limit=5&format=json&apiKey=' +
            import.meta.env.VITE_GEOAPIFY_API_KEY
        );

        return geoapify.get(requestUrl);
    },
    getGeoapifyMapStyle() {

        const mapStyleUrl = 'https://maps.geoapify.com/v1/styles/osm-carto/style.json?apiKey=' + import.meta.env.VITE_GEOAPIFY_API_KEY ;
        return geoapify.get(mapStyleUrl);
    }
}