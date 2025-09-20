// src/api.ts
import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080/api/v1/tasklists", 
});

export interface Tasklist {
  id?: string;
  title: string;
  description: string;
  status: "PENDENTE" | "CONCLUÍDA";
}

export default api;
