import {
  Card,
  CardContent,
  CardActions,
  Typography,
  Button,
} from "@mui/material";
import { Done, Edit, Delete } from "@mui/icons-material";
import type { Tasklist } from "../models/Tasklist";

interface TaskCardProps {
  task: Tasklist;
  onConclude: (task: Tasklist) => void;
  onEdit: (task: Tasklist) => void;
  onDelete: (id?: string) => void;
}

export function TaskCard({ task, onConclude, onEdit, onDelete }: TaskCardProps) {
  return (
    <Card sx={{ textAlign: "left" }}>
      <CardContent>
        <Typography variant="h6">{task.title}</Typography>
        <Typography color="text.secondary">{task.description}</Typography>
        <Typography variant="body2" sx={{ mt: 1 }}>
          Status: {task.status}
        </Typography>
      </CardContent>
      <CardActions>
        {task.status !== "concluída" && (
          <Button
            size="small"
            color="success"
            startIcon={<Done />}
            onClick={() => onConclude(task)}
          >
            Concluir
          </Button>
        )}
        <Button
          size="small"
          color="primary"
          startIcon={<Edit />}
          onClick={() => onEdit(task)}
        >
          Editar
        </Button>
        <Button
          size="small"
          color="error"
          startIcon={<Delete />}
          onClick={() => onDelete(task.id)}
        >
          Excluir
        </Button>
      </CardActions>
    </Card>
  );
}
