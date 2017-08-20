import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Home from '@/components/Home'

Vue.use(Router)

export default new Router({
  base:'/',
  mode:'history',
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path:'/home',
      name:'Home',
      component:Home
    },
    {
      path:"/",
      redirect:'/login'
    }
  ]
})
