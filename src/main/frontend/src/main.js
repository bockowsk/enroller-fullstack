import Vue from 'vue';
import App from './App.vue';
import VueResource from "vue-resource";

Vue.use(VueResource);
Vue.http.options.root = '/api';
Vue.http.headers.common['Authorization'] = 'Basic YXBpOnBhc3N3b3Jk';

new Vue({
    el: '#app',
    http: {
    	root: '/root',
    	headers: {
    		Authorization: 'Basic YXBpOnBhc3N3b3Jk'
        }
    },
    render: h => h(App)
});
