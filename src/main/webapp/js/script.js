function loadStatuses() {
  fetch('/list-statuses').then(response => response.json()).then((statuses) => {
    const statusListElement = document.getElementById('status-list');
    statuses.forEach((status) => {
      statusListElement.appendChild(createStatusElement(status));
    })
  });
}

/** Creates an element that represents a status, including its delete button. */
function createStatusElement(status) {
  const statusElement = document.createElement('li');
  statusElement.className = 'status';

  const titleElement = document.createElement('span');
  titleElement.innerText = status.title;

  const deleteButtonElement = document.createElement('button');
  deleteButtonElement.innerText = 'Delete';
  deleteButtonElement.addEventListener('click', () => {
    deleteStatus(status);

    // Remove the status from the DOM.
    statusElement.remove();
  });

  statusElement.appendChild(titleElement);
  statusElement.appendChild(deleteButtonElement);
  return statusElement;
}

/** Tells the server to delete the status. */
function deleteStatus(status) {
  const params = new URLSearchParams();
  params.append('id', status.id);
  fetch('/delete-status', {method: 'POST', body: params});
}
