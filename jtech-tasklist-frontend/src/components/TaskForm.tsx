import { Grid, TextField, Button } from "@mui/material";
import type { Tasklist } from "../models/Tasklist";

interface TaskFormProps {
  task: Tasklist;
  setTask: (task: Tasklist) => void;
  onSave: () => void;
}

export function TaskForm({ task, setTask, onSave }: TaskFormProps) {
  return (
    <Grid container spacing={2} justifyContent="center" sx={{ mb: 3 }}>
      <Grid item xs={12} sm={5}>
        <TextField
          label="Título"
          fullWidth
          value={task.title}
          onChange={(e) => setTask({ ...task, title: e.target.value })}
        />
      </Grid>
      <Grid item xs={12} sm={5}>
        <TextField
          label="Descrição"
          fullWidth
          value={task.description}
          onChange={(e) => setTask({ ...task, description: e.target.value })}
        />
      </Grid>
      <Grid item xs={12} sm={2}>
        <Button
          variant="contained"
          fullWidth
          sx={{ height: "100%" }}
          onClick={onSave}
        >
          {task.id ? "Salvar" : "Adicionar"}
        </Button>
      </Grid>
    </Grid>
  );
}
