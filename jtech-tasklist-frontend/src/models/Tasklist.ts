export interface Tasklist {
  id?: string;
  title: string;
  description: string;
  status: "PENDENTE" | "CONCLUÍDA";
}
