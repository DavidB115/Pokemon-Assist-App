<template>
    <div class="landmark-details">
        <global-header />
        
        <div class="details-container">
            <p class="landmark-id">{{landmark.landmarkId}}</p>
            <p class="detail-name">{{landmark.landmarkName}}</p>
            <p class="detail-value">{{landmark.landmarkAddress}}</p>
            <p class="detail-value">{{landmark.landmarkDetails}}</p>
            <img class="landmark-image" :src="imageBaseUrl + landmark.landmarkId + '.jpg'">
            <!-- <p>{{landmark.landmarkLatitude}}</p>
            <p>{{landmark.landmarkLongitude}}</p> -->
            <router-link v-bind:to="{ name: 'landmarks' }">
                <button>Landmark List</button>
            </router-link>
            <!-- <button class="btn btn-cancel ml-3 mt-4" v-if="isAdmin" v-on:click="deleteLandmark">Delete Landmark</button> -->
        </div>
        
    </div>
    <global-footer />
</template>

<script>
import GlobalHeader from '../components/GlobalHeader.vue';
import GlobalFooter from '../components/GlobalFooter.vue';
import LandmarkService from '../services/LandmarkService';


export default {
    components: {
        GlobalHeader,
        GlobalFooter,
    },
    data() {
        return {
            landmark: {},
            imageBaseUrl: '/src/assets/landmarkImages/'
        }
    },
    computed: {
        isAdmin() {
        if (this.$store.state.user.authorities) {
        return this.$store.state.user.authorities[0].name === 'ROLE_ADMIN';
        } else {
        return false;
        }
    },
    
    },
    methods: {
        getLandmark(){
            this.landmark = this.$store.state.landmarks.find(landmark => {
                return this.$route.params.landmarkId == landmark.landmarkId;
            })
        },
        // deleteLandmark() {
        //     if (
        //         confirm('Are you sure you want to delete this landmark?')) {
        //             LandmarkService.deleteLandmark(this.landmark.landmarkId)
        //             .then(response => {
        //                 if (response.status === 200) {
        //                     this.$store.commit('SET_NOTIFICATION', {
        //                         message: `Landmark ${this.landmark.landmarkId} was deleted.`,
        //                         type: 'success'
        //                     })
        //                     this.$router.push({ name: 'landmarks' })
        //                 }
        //             })
        //         }
            
        // }
    },
    created(){
        this.getLandmark();
    } 
    
}

</script>

<style scoped>
    .details-container{
        padding: 20px;
        background-color: #f9f9f9;
        border-radius: 10px;
        margin: 20px auto;
        color: #333;
        /* border: 2px solid rgb(236, 173, 231); */
        max-width: 600px;
        width: 100%;
        /* background-color: rgb(59, 108, 172); */
        text-align: center;
        margin-top: 100px;
        margin-bottom: 100px;
    }

    .landmark-image{
        max-width: 100%;
        border-radius: 8px;
        display: block;
        margin: 0 auto;
        margin-top: 40px;
        margin-bottom: 50px;
        /* border: 1px solid antiquewhite; */
        text-decoration: none;
        height: 88%;
        width: 88%;
    }

    .detail-name{
        font-weight: bold;
        font-size: 18px;
        text-decoration: none;
    }

    .landmark-id{
        display: none;
    }

    button{
        padding: 12px 24px;
        background-color: #4caf50;
        color: white;
        font-weight: bold;
        border: none;
        border-radius: 20px;
        display: inline block;
        cursor: pointer;
        transition: background-color 0.3s ease;
        margin-bottom: 30px;
        text-decoration: none;
    }

    button:hover{
        background-color: #45a049;
    }

    /* Mobile Styles   */
   @media screen and (max-width: 600){
    .details-container{
        padding: 300px;
        margin-left: 70px;
    }
   }
</style>
