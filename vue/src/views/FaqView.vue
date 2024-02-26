<template>
    <global-header />
        <h1 class="row justify-content-center mt-5">Frequently Asked Questions</h1>
        <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <ul class="bullet-hole">
                    <li>
                        <strong>How does Waypoint work?</strong>
                        <p>
                            Waypoint is a content platform where travelers can find and share information
                            about destinations and itineraries. Users can create itineraries, view photos, and
                            get recommendations for local landmarks.
                        </p>
                    </li>
                    <li>
                        <strong>How can I find the best deals on hotels and flights?</strong>
                        <p>
                            Waypoint does not provide a search feature for hotels, flights, and vacation rentals.
                             You can compare prices from various booking sites and find the best deals. 
                             Additionally, consider checking for special offers and promotions.
                        </p>
                    </li>
                    <li>
                        <strong>How can I plan a trip using Waypoint?</strong>
                        <p>
                            Use Waypoint to research destinations, build and share itineraries, 
                            and plan activities. Explore photos, and use the map to find destinations.
                        </p>
                    </li>
                    <li>
                        <strong>What should I do if I encounter a problem during my trip?</strong>
                        <p>
                            If you encounter issues, contact the business directly or your travel provider. 
                            Additionally, you should always check the local weather and use any resources to ensure areas
                            are safe. 
                        </p>
                    </li>
                    
                </ul>
            </div>
        </div>
    </div>
    <global-footer />
</template>

<script>
import GlobalHeader from "../components/GlobalHeader.vue";
import GlobalFooter from "../components/GlobalFooter.vue";
export default {
  components: {
    GlobalHeader,
    GlobalFooter,
  },
};
</script>


<style scoped>
.bullet-hole {
    display: inline-block;
    /* width: 12px; */
    /* height: 12px; */
}
</style>