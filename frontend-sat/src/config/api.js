import axios from "axios";

const port = 8181
const address = "localhost"

const apiClient = axios.create({
  baseURL: `http://${address}:${port}/api`, 
  timeout: 10000, 
});

export default apiClient;
