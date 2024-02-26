import axios from "axios";

export default {
    getAllLandmarks(){
        return axios.get("/landmark")
    },
    getLandmark(id){
        return axios.get(`/landmark/${id}`)
    },
    addLandmark(landmark){
        return axios.post("/landmark", landmark)
    },
    // deleteLandmark(landmarkId) {
    //     return axios.delete(`/landmark/${landmarkId}`)
    // }
}