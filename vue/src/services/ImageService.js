// took from lecture final for dummy data
//Not sure if its an exact copy from final lecture

import axios from "axios";
import { mapActions } from "vuex";

export default {
  addImage(file) {
    const form = new FormData();
    form.append("data", file);
    return axios.post("/images", form, {
      headers: {
        "content-type": "multipart/form-data",
      },
    });
  },
  getImageIds() {
    return axios.get("/images/imageids");
  },
};
