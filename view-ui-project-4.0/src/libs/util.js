let util = {

};
util.title = function (title) {
    title = title ? title + ' - Home' : 'SanJuTou';
    window.document.title = title;
};

export default util;
