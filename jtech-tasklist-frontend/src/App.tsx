import { Container, Typography, Grid } from "@mui/material";
import { useTasklists } from "./controllers/useTasklists";
import { TaskForm } from "./components/TaskForm";
import { TaskCard } from "./components/TaskCard";

function App() {
  const {
    tasklists,
    currentTask,
    setCurrentTask,
    saveTask,
    updateStatus,
    deleteTask,
    editTask,
  } = useTasklists();

  return (
    <Container maxWidth="md" sx={{ mt: 6, textAlign: "center" }}>
      <Typography variant="h4" gutterBottom>
        Gerenciar Tasklists
      </Typography>

      <TaskForm task={currentTask} setTask={setCurrentTask} onSave={saveTask} />

      <Grid container spacing={2} justifyContent="center">
        {tasklists.map((task) => (
          <Grid item xs={12} sm={6} md={4} key={task.id}>
            <TaskCard
              task={task}
              onConclude={updateStatus}
              onEdit={editTask}
              onDelete={deleteTask}
            />
          </Grid>
        ))}
      </Grid>
    </Container>
  );
}

export default App;
