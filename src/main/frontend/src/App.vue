<template>
  <div id="app">
    <h1>
      <img src="./assets/logo.svg" alt="Enroller" class="logo">
      System do zapisów na zajęcia
    </h1>
    <div v-if="authenticatedUsername">
      <h2>Witaj {{ authenticatedUsername }}!
        <a @click="logout()" class="float-right  button-outline button">Wyloguj</a>
      </h2>
      <meetings-page :username="authenticatedUsername"></meetings-page>
    </div>
    <div v-else>
      <main-form @login="login($event)"></main-form>
    </div>
  </div>
</template>

<script>
    import "milligram";
    import MainForm from "./MainForm";
    import MeetingsPage from "./meetings/MeetingsPage";

    export default {
        components: {MainForm, MeetingsPage, },
        data() {
            return {
                authenticatedUsername: "",
                registeredUser: "",
            };
        },
        methods: {
            login(user) {
                this.authenticatedUsername = user.login;
            },
            register(user) {
                this.registeredUsername = user.login;
            },
            logout() {
                this.authenticatedUsername = '';
            }
        }
    };
</script>

<style>
  #app {
    max-width: 1000px;
    margin: 0 auto;
  }

  .logo {
    vertical-align: middle;
  }
</style>

