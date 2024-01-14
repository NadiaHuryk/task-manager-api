export interface Task {
  id?: number;
  title: string;
  description: string;
  priority: string;
  status: string;
  dueDate: string;
  userId: number | null;
  taskGroupId: number | null;
  checked?: boolean;
}
