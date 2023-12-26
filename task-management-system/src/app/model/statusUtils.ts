export interface StatusMap {
  [key: string]: string;
}

export function formatStatus(status: string): string {
  const statusMap: StatusMap = {
    'NOT_STARTED': 'Not started',
    'IN_PROGRESS': 'In progress',
    'COMPLETED': 'Completed'
  };

  return statusMap[status] || status;
}
