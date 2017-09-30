import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home';
import Chart from '@/components/Chart';
import setting from "@/components/setting";

Vue.use(Router)

export default new Router({
  base: '/',
  // mode: 'history',
  routes: [
    {
      path: '/chart',
      name: 'chart',
      component: Chart
    },
    {
      path: '/home',
      name: 'Home',
      component: Home
    },
    {
      path: "/setting",
      name: "setting",
      component: setting
    },
    {
      path: "/",
      redirect: '/home'
    }
  ]
})
