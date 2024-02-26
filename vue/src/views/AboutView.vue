<template>
  <global-header />
  <div class="about-page">
    <h1 class="word">{{ displayedText }}</h1>
    <hr class="divider-header" />
    <div class="about-cards">
      <!-- Card 1 -->
      <div class="about-card">
        <div
          class="about-card-inner"
          data-aos="fade-down"
          data-aos-easing="linear"
          data-aos-duration="1500"
          data-aos-offset="100"
        >
          <div class="about-details">
            <h2 class="name-text">Jason Snoddy</h2>
            <hr class="divider" />
            <p>
              Hello, I'm <span class="mobile-name">Jason Snoddy</span>
              a junior software engineer. Recently, I finished Tech Elevator's
              Full-Stack Developer course. I am currently searching for software
              engineer jobs focusing on the back-end.
            </p>
            <p>
              I rely on both my intelligence and will-power to follow through on
              projects and find the best possible solution to problems I face. I
              also love playing music, frisbee, and being in the outdoors with a
              good book. You'll find me on the side of a mountain reading some
              good Tolkien.
            </p>
          </div>
          <div class="about-profile">
            <div class="about-img-container">
              <img
                class="about-img"
                src="../assets/teamImages/jasonLogo.jpg"
                alt="Jason"
              />
            </div>
            <div class="social-links">
              <a
                href="https://www.linkedin.com/in/jasonsnoddy-programmer-musician/"
                target="_blank"
              >
                <img
                  class="social-icon"
                  src="../assets/teamImages/linkedin.png"
                  alt="LinkedIn"
                />
              </a>
              <a href="https://github.com/jasonsnod" target="_blank">
                <img
                  class="social-icon"
                  id="github"
                  src="../assets/teamImages/github.png"
                  alt="GitHub"
                />
              </a>
            </div>
          </div>
        </div>
      </div>

      <!-- Card 2 -->
      <div class="about-card">
        <div
          class="about-card-inner"
          data-aos="fade-left"
          data-aos-easing="linear"
          data-aos-duration="1500"
          data-aos-offset="100"
        >
          <div class="about-details">
            <h2 class="name-text">David Boyd</h2>
            <hr class="divider" />
            <p>
              Hello, I'm <span class="mobile-name">David Boyd</span> a junior Full-Stack developer. 
              I've cherished video games for their adaptable nature, allowing me to make 
              nuanced choices without the burden of handling the entire world's scope. 
              This flexibility not only drew me into gaming but also spurred my 
              interest in poetry and ignited my passion for coding. 
            </p>
            <p>
              My journey as a Full-Stack developer has blended the refined critical thinking from my Bachelor's Degree in English with the creativity cultivated through 
              publishing poetry thrice. This fusion aids me in dissecting coding challenges and finding innovative solutions. 
              I eagerly anticipate further growth as I transition into a software development role.
            </p>
          </div>
          <div class="about-profile">
            <div class="about-img-container">
              <img
                class="about-img"
                src="../assets/teamImages/davidLogo.jpg"
                alt="Jason"
              />
            </div>
            <div class="social-links">
              <a
                href="https://www.linkedin.com/in/davidboyddeveloper/"
                target="_blank"
              >
                <img
                  class="social-icon"
                  src="../assets/teamImages/linkedin.png"
                  alt="LinkedIn"
                />
              </a>
              <a href="https://github.com/jasonsnod" target="_blank">
                <img
                  class="social-icon"
                  id="github"
                  src="../assets/teamImages/github.png"
                  alt="GitHub"
                />
              </a>
            </div>
          </div>
        </div>
      </div>

      <!-- Card 3 -->
      <div class="about-card">
        <div
          class="about-card-inner"
          data-aos="fade-right"
          data-aos-easing="linear"
          data-aos-duration="1500"
          data-aos-offset="100"
        >
          <div class="about-details">
            <h2 class="name-text">Zach Huster</h2>
            <hr class="divider" />
            <p>
              Hello, I'm <span class="mobile-name">Zach Huster</span>
              Welcome to my digital space! I'm a software developer with a zest for life, 
              and I'm here to share my journey with you. If you're a fellow tech enthusiast,
               a gamer looking for a teammate, or simply someone curious about the exciting 
               world of software development, you're in the right place!
            </p>
            <p>
              My passion for coding began as a spark and has since grown into a blazing fire. 
              I thrive on tackling complex challenges and transforming them into elegant solutions. 
              Whether it's crafting efficient algorithms or building user-friendly 
              applications, I'm constantly exploring the ever-evolving landscape of technology.
            </p>
          </div>
          <div class="about-profile">
            <div class="about-img-container">
              <img
                class="about-img"
                src="../assets/teamImages/zh-about.png"
                alt="Jason"
              />
            </div>
            <div class="social-links">
              <a href="https://www.linkedin.com/in/zachhuster/" target="_blank">
                <img
                  class="social-icon"
                  src="../assets/teamImages/linkedin.png"
                  alt="LinkedIn"
                />
              </a>
              <a href="https://github.com/huster8" target="_blank">
                <img
                  class="social-icon"
                  id="github"
                  src="../assets/teamImages/github.png"
                  alt="GitHub"
                />
              </a>
            </div>
          </div>
        </div>
      </div>

      <!-- Card 4 -->
      <div class="about-card">
        <div
          class="about-card-inner"
          data-aos="fade-up"
          data-aos-easing="linear"
          data-aos-duration="1500"
          data-aos-offset="100"
        >
          <div class="about-details">
            <h2 class="name-text">Ashton Little</h2>
            <hr class="divider" />
            <p>
              Hello, I'm <span class="mobile-name">Ashton Little</span>
              a enthusiastic software developer. Completed various advanced
              courses in web development and continuously engaged in solo
              projects to enhance expertise. Currently pursuing additional
              learning opportunities to expand programming skills further.
            </p>
            <p>
              Known for a proactive problem-solving approach and a talent for
              crafting visually appealing and user-friendly web experiences.
              Outside of coding, passionate about cycling, Nintendo, guitars,
              vinyl, raw denim and of course The Grateful Dead.
            </p>
          </div>
          <div class="about-profile">
            <div class="about-img-container">
              <img
                class="about-img"
                src="../assets/teamImages/ashtonLogo.jpg"
                alt="Jason"
              />
            </div>
            <div class="social-links">
              <a
                href="https://www.linkedin.com/in/ashton-codes/"
                target="_blank"
              >
                <img
                  class="social-icon"
                  src="../assets/teamImages/linkedin.png"
                  alt="LinkedIn"
                />
              </a>
              <a href="https://github.com/ashton8504" target="_blank">
                <img
                  class="social-icon"
                  id="github"
                  src="../assets/teamImages/github.png"
                  alt="GitHub"
                />
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
    <global-footer />
  </div>
</template>

<script>
import GlobalHeader from "../components/GlobalHeader.vue";
import GlobalFooter from "../components/GlobalFooter.vue";
export default {
  components: {
    GlobalHeader,
    GlobalFooter,
  },
  data() {
    return {
      words: [
        "Meet the Team!",
        "Jason Snoddy",
        "David Boyd",
        "Zach Huster",
        "Ashton Little",
      ],
      part: "",
      i: 0,
      offset: 0,
      len: 0,
      forwards: true,
      skip_count: 0,
      skip_delay: 15,
      speed: 70,
      displayedText: "",
    };
  },
  mounted() {
    window.initAOS();
    this.len = this.words.length;
    this.wordflick();
  },
  methods: {
    wordflick() {
      setInterval(() => {
        if (this.forwards) {
          if (this.offset >= this.words[this.i].length) {
            ++this.skip_count;
            if (this.skip_count === this.skip_delay) {
              this.forwards = false;
              this.skip_count = 0;
            }
          }
        } else {
          if (this.offset === 0) {
            this.forwards = true;
            this.i++;
            this.offset = 0;
            if (this.i >= this.len) {
              this.i = 0;
            }
          }
        }
        this.part = this.words[this.i].substr(0, this.offset);
        if (this.skip_count === 0) {
          if (this.forwards) {
            this.offset++;
          } else {
            this.offset--;
          }
        }
        this.displayedText = this.part;
      }, this.speed);
    },
  },
};
</script>

<style scoped>
@font-face {
  font-family: "RetroFont";
  src: url("../assets/fonts/retroFont.ttf") format("truetype");
}

.about-page {
  padding: 20px;
}

.about-header {
  text-align: center;
  margin-top: 80px;
  margin-bottom: 50px;
  font-family: "Arial", sans-serif;
  color: #e4e1e1;
  text-transform: uppercase;
}

.about-cards {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  max-width: 900px;
  margin: 0 auto;
}

.about-card {
  width: 100%;
  background-color: #f9f9f9;
  border-radius: 16px;
  margin-bottom: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease-in-out;
  display: flex;
  border: 3px solid #4267b9;
  margin-bottom: 50px;
  display: flex;
  justify-content: flex-end;
  align-items: flex-start;
}

.about-card:hover {
  transform: translateY(-5px);
}

.about-card-inner {
  display: flex;
  align-items: center;
  flex: 1;
  padding: 20px;
}

.about-img-container {
  width: 200px;
  height: 200px;
  overflow: hidden;
  border-radius: 50%;
  margin-right: 20px;
  border: 2px solid #333;
}

.about-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.social-links {
  display: flex;
  justify-content: center;
  margin-top: 5px;
  margin-right: 15px;
}

.social-links a {
  display: inline-block;
  margin: 0 5px;
}

.social-icon {
  width: 50px;
  height: auto;
}

.about-details {
  flex: 1;
}

.name-text {
  font-size: 24px;
  font-family: "RetroFont", sans-serif;
  text-align: center;
  margin-bottom: 10px;
  color: #333333;
  letter-spacing: 1px;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
}

.divider {
  border: none;
  border-top: 1px solid #7f7e7e;
  margin: 20px 0;
}

.divider-header {
  width: 30%;
  margin: auto;
  border: 1px solid #f4f2f2;
  margin-bottom: 60px;
}

.about-details p {
  font-size: 16px;
  line-height: 1.6;
  color: #666;
}

.word {
  text-align: center;
  margin: auto;
  color: white;
  font: 700 normal 2.5em "tahoma";
  text-shadow: 5px 2px #222324, 2px 4px #222324, 3px 5px #222324;
  margin-bottom: 40px;
  height: 100px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.mobile-name {
  display: none;
}

/* Mobile Styling */
@media only screen and (max-width: 768px) {
  .about-cards {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .about-card {
    width: calc(100% - 40px);
    margin: 0 auto 20px;
  }

  .about-card-inner {
    flex-direction: column-reverse;
    text-align: center;
  }

  .about-img-container {
    width: 150px;
    height: 150px;
    margin-right: 0;
    margin-bottom: 10px;
  }

  .name-text {
    font-size: 20px;
  }

  .about-details p {
    font-size: 14px;
  }

  .divider-header {
    margin-bottom: 30px;
  }

  .about-details .name-text {
    display: none;
  }
}

@media only screen and (max-width: 600px) {
  .mobile-name {
    display: inline;
    font-weight: bold;
    color: #ff6600;
    font-size: 1.2em;
  }
}
</style>
