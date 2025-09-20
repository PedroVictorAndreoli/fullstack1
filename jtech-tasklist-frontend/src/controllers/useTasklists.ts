import { useEffect, useState } from "react";
import { tasklistService } from "../services/tasklistService";
import type { Tasklist } from "../models/Tasklist";

export function useTasklists() {
  const [tasklists, setTasklists] = useState<Tasklist[]>([]);
  const [currentTask, setCurrentTask] = useState<Tasklist>({
    title: "",
    description: "",
    status: "PENDENTE",
  });

  const fetchTasklists = async () => {
    const res = await tasklistService.getAll();
    setTasklists(res.data);
  };

  const saveTask = async () => {
    if (!currentTask.title.trim()) return;

    if (currentTask.id) {
      await tasklistService.update(currentTask.id, currentTask);
    } else {
      await tasklistService.create(currentTask);
    }

    resetForm();
    fetchTasklists();
  };

  const updateStatus = async (task: Tasklist) => {
    if (!task.id) return;
    await tasklistService.update(task.id, { ...task, status: "CONCLUÍDA" });
    fetchTasklists();
  };

  const deleteTask = async (id?: string) => {
    if (!id) return;
    await tasklistService.delete(id);
    fetchTasklists();
  };

  const editTask = (task: Tasklist) => {
    setCurrentTask(task);
  };

  const resetForm = () => {
    setCurrentTask({ title: "", description: "", status: "PENDENTE" });
  };

  useEffect(() => {
    fetchTasklists();
  }, []);

  return {
    tasklists,
    currentTask,
    setCurrentTask,
    saveTask,
    updateStatus,
    deleteTask,
    editTask,
    resetForm,
  };
}
