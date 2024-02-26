<template>
  <div class="home">
    <global-header />
    <h1>Welcome {{ this.$store.state.user.username }}</h1>
    <p>Let's Add an Image to your list of images:</p>
    <file-upload />

    <image-viewer />
  </div>
</template>

<script>
import GlobalHeader from '../components/GlobalHeader.vue';
import FileUpload from '../components/FileUpload.vue';
import ImageViewer from '../components/ImageViewer.vue';

export default {
  components: {
    GlobalHeader,
    FileUpload,
    ImageViewer
  }
}
</script>
