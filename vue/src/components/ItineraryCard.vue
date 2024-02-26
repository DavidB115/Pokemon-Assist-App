<template>
    <div class="itinerary-container">
        <router-link :to="{ name: 'itinerary-details', params: {itineraryId: itinerary.itineraryId}}">
            <h1 class="itinerary-name">{{ itinerary.itineraryName }}</h1>
        </router-link>
        <p class="itinerary-info">{{ itinerary.itineraryId}}</p>
        <p class="itinerary-info">{{ itinerary.userId }}</p>
        <p class="itinerary-address">{{ itinerary.startingAddress }}</p>
    </div>
</template>

<script>

export default {
    props: {
        itinerary: Object
    },
    components: {

    }
}

</script>

<style scoped>
    .itinerary-container {
    background-color: #345995;
    border-radius: 10px;
    padding: 20px;
    margin: 20px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
    border: 1px solid rgb(236, 173, 231);
    color: #E2ECF7;
}
    .itinerary-name {
        font-size: 32px;
        color: #FFFFFF;
        margin-bottom: 15px;
        text-align: center;
        font-family: "Roboto", sans-serif;
        text-shadow: 3px 2px 4px rgba(0, 0, 0, 0.9);
        text-decoration: none !important;
    }
    .itinerary-info {
        font-size: 18px;
        color: #FFFFFF;
        margin-bottom: 10px;
        text-align: center;
        opacity: 0.7;
        display: none;
    }
    .itinerary-address {
        font-size: 20px;
        color: #FFFFFF;
        font-style: italic;
        text-align: center;
        opacity: 0.9;
        color: rgb(253, 243, 229);
        text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.9);
    }
</style>
