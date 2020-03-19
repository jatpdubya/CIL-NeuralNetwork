// Grab our HTML elements
const showWeightsBtn = document.querySelector('#show-weights')
const infoSection = document.querySelector('#info-section')
const infoItem = document.querySelector('#info-item')
const ApiURL = 'http://localhost:8080/api/v1/weights'

infoSection.style.display = 'none'

// Add Event Listeners
loadEventListeners()

function loadEventListeners() {
  // Show Weights button
  showWeightsBtn.addEventListener('click', showWeights)
}

function showWeights(e) {
  fetch(ApiURL)
  .then((response) => {
    if (response.ok) {
      response.json()
      .then((data) => {
        getWeights(data)
      })
    } else {
      alert('Error Fetching Weights')
    }
  })
  infoSection.style.display = 'block'

  e.preventDefault()
}

function getWeights(data) {
  Array.from(infoSection.children).forEach(function(child) {
    child.remove()
  })
  for (i in data) {
    par = document.createElement('p')
    par.id = 'info-item'
    par.textContent = `id: ${data[i].id} val: ${data[i].val}`
    infoSection.appendChild(par)
  }
}