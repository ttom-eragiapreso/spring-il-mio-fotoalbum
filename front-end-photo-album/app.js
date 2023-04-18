console.log("ciao");

const { createApp } = Vue;

createApp({
  data() {
    return {
      message: "ciao vue",
      apiUrl: "http://localhost:8080/api/photo",
      messageUrl: "http://localhost:8080/api/user-message",
      response: {},
      keyword: "",
      user_email: "",
      user_content: ""
    };
  },

  methods: {
    loadData() {
      axios.get(this.apiUrl + `?q=${this.keyword}`).then((response) => {
        console.log(response);
        this.response = response;
      });
    },

    sendMessage() {
      axios
        .post(this.messageUrl, {
          email: this.user_email,
          content: this.user_content
        })
        .catch((error) => console.log(error))
        .then((response) => {
          console.log(response.data);
          this.user_content = "";
          this.user_email = "";
          this.loadData();
        });
    },

    classSwitch(cat) {
      switch (cat.name) {
        case "Roma":
          return "btn btn-success";
        case "Paesaggi":
          return "btn btn-secondary";
        case "Live Music":
          return "btn btn-info";
        case "Animali":
          return "btn btn-warning";
        case "Glasgow":
          return "btn btn-danger";
      }
    }
  },

  mounted() {
    this.loadData();
  }
}).mount("#app");
