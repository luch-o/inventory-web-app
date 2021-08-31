<template>
  <div id="Account">
    <div class="container">
      <h2>
        Titular Cuenta: <span>{{ username }}</span>
      </h2>
      <h2>
        Saldo: <span>${{ accountByUserId.balance }} COP</span>
      </h2>
      <h2>
        Fecha Ultimo Movimiento:
        <span>{{ accountByUserId.lastChange.substring(0, 10) }}</span>
      </h2>
      <h2>
        Hora Ultimo Movimiento:
        <span>{{ accountByUserId.lastChange.substring(11, 19) }}</span>
      </h2>
    </div>
  </div>
</template>

<script>
import gql from "graphql-tag";

export default {
  name: "Account",

  data: function () {
    return {
      username: "none",
      accountByUserId: {
        balance: "null",
        lastChange: "null",
      }
    };
  },

  created: function () {
    this.username = this.$route.params.username;
  },

  apollo: {
    accountByUserId: {
      query: gql`
        query ($accountByUserIdUserId: String!) {
          accountByUserId(userId: $accountByUserIdUserId) {
            balance
            lastChange
            userId
          }
        }
      `,
      variables() {
        return {
          accountByUserIdUserId: localStorage.getItem("user_id"),
        };
      },
    },
  },
};
</script>


<style>
#Account {
  width: 100%;
  height: 120%;

  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

.container {
  padding: 70px;
  border: 1px solid rgba(0, 0, 0, 0.3);
  border-radius: 20px;
}

#Account h2 {
  font-size: 30px;
  color: #283747;
}
#Account span {
  color: crimson;
  font-weight: bold;
}
</style>