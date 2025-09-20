import api from "../interfaces/api";
import type { Tasklist } from "../models/Tasklist";

export const tasklistService = {
  getAll: () => api.get<Tasklist[]>(""),
  create: (task: Tasklist) => api.post("", task),
  update: (id: string, task: Tasklist) => api.put(`/${id}`, task),
  delete: (id: string) => api.delete(`/${id}`),
};
