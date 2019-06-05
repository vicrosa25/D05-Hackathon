start transaction;

use `acme-news`;

revoke all privileges on `acme-news`.* from 'acme-user'@'%';

revoke all privileges on `acme-news`.* from 'acme-manager'@'%';

drop user 'acme-user'@'%';

drop user 'acme-manager'@'%';

drop database `acme-news`;

commit;