let util = {

};
util.title = function (title) {
    title = title ? title + ' - SanJuTou' : 'SanJuTou';
    window.document.title = title;
};

export default util;
