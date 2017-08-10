import { EmailwebPage } from './app.po';

describe('emailweb App', () => {
  let page: EmailwebPage;

  beforeEach(() => {
    page = new EmailwebPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
