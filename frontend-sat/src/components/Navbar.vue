<template>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container-fluid">
        <router-link to="/" class="navbar-brand">SAT</router-link>
        
        <button
          class="navbar-toggler"
          type="button">
        </button>
  
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav">
            <li class="nav-item">
              <router-link to="/" class="nav-link" exact-active-class="active">
                Início
              </router-link>
            </li>
            <li class="nav-item">
              <router-link to="/extrato" class="nav-link" exact-active-class="active">
                Extratos
              </router-link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </template>
  
  <script>
  export default {
    name: "Navbar",
  };
  </script>
  
  <style scoped>
  .navbar-nav .nav-link.active {
    font-weight: bold;
    border-bottom: 2px solid #fff;
  }
  </style>