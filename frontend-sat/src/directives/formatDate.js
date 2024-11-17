export default {
  name: 'formatDate',
  mounted(el, binding) {
    const data = binding.value;
    if (!data) return;
    const dataObj = new Date(data);
    const dia = String(dataObj.getDate()).padStart(2, '0');
    const mes = String(dataObj.getMonth() + 1).padStart(2, '0');
    const ano = dataObj.getFullYear();
    el.textContent = `${dia}/${mes}/${ano}`;
  },
};
