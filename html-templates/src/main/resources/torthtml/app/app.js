$(function () {
  $('#tree').jstree({
    "plugins": [ 'wholerow', 'types' ],
    types: {
      valid_children: ['passed', 'failed'],
      passed: {
        icon: 'glyphicon glyphicon-ok-circle passed'
      },
      failed: {
        icon: 'glyphicon glyphicon-remove-circle failed'
      },
      'default': {
        type: 'passed'
      }
    },
    'core': {
      'data': [
        {
          text: "Creating an External Link: ExternalLink [title='uNmdn1iw1QNzPm16QTAv3z7FaMJlt3', href='', hint='uyA1oHGRrPBEMZu8l9ORG0eUTSJKK2']..",
          type: 'passed',
          children: [
            {
              text: 'Opening External Links dialog',
              type: 'passed',
              children: [
                {text: "Checking whether we're in small screen mode", type: 'passed'},
                {text: "We're in a wide screen mode", type: 'passed'},
                {text: "Opening Administration context menu on top of the page", type: 'passed'},
                {text: "Choosing Enter Admin Mode menu item", type: 'passed', children: [
                  {text: "Checking whether we're in small screen mode", type: 'passed'},
                  {text: "We're in a wide screen mode", type: 'passed'}
                ]},
                {text: "Clicking a button to open External Links Editor", type: 'passed'}
              ]
            },
            {text: "Pressing on Add Link button", type: 'passed'}
          ]
        },
        {
          text: "Fill new link data",
          type: 'passed',
          children: [
            { text: "Fill Link Title field: uNmdn1iw1QNzPm16QTAv3z7FaMJlt3", type: 'passed' },
            { text: "Fill Link Href field: ", type: 'passed' },
            { text: "Fill Link Hint field: uyA1oHGRrPBEMZu8l9ORG0eUTSJKK2", type: 'passed' }
          ]
        },
        {text: "Clicking Save Link button", type: 'passed'},
        {text: "Closing dialog by pressing Close button", type: 'passed'},
        {text: "Checking whether the link is actually visible on the page", type: 'passed'},
        {text: "The link was found!", type: 'passed'},
        {
          text: "Removing External Link: ExternalLink [title='uNmdn1iw1QNzPm16QTAv3z7FaMJlt3', href='', hint='uyA1oHGRrPBEMZu8l9ORG0eUTSJKK2']",
          type: 'passed',
          state: { 'opened': true},
          children: [
            {text: "Opening External Links dialog", type: 'passed', children: [
              {text: "Checking whether we're in small screen mode", type: 'passed'},
              {text: "We're in a wide screen mode", type: 'passed'}
            ]},
            {text: "Clicking a button to open External Links Editor", type: 'passed'},
            {text: "Clicking Remove Link button", type: 'passed'},
            {text: "Closing dialog by pressing Close button", type: 'failed', state: {selected: true}}
          ]
        }
      ]
    }
  })
  ;
})
;
