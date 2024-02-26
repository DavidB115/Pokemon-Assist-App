import axios from 'axios';

export default {
    getAllItineraries(userId) {
        return axios.get(`/itinerary/${userId}`);
    },
    getLandmarksByItinerary(itineraryId) {
        return axios.get(`/itinerary/${itineraryId}/landmark`);
    },
    editLandmarksForItinerary(itineraryId, landmarkIdArray) {
        let landmarkIdString = landmarkIdArray.join(',')
        //?landmarkIds=${landmarkIdString}
        console.log(landmarkIdString);
        return axios.put(`/itinerary/${itineraryId}/landmark?landmarkIds=${landmarkIdString}`);
    },
    createLandmarksForItinerary(itineraryId, landmarkIdArray) {
        console.log(landmarkIdArray);
        let landmarkIdString = landmarkIdArray.join(',');
        //?landmarkIds=${landmarkIdString}
        console.log(landmarkIdString);
        return axios.post(`/itinerary/${itineraryId}/landmark?landmarkIds=${landmarkIdString}`);
    },
    deleteItinerary(itineraryId) {
        return axios.delete(`/itinerary/${itineraryId}`);
    },
    createBaseItinerary(itinerary) {
        return axios.post('/itinerary', itinerary);
    },
    updateItineraryDetails(itineraryId, itinerary) {
        return axios.put(`/itinerary/${itineraryId}`, itinerary);
    },


}