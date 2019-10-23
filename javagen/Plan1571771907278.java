public class Plan1571771907278 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

for (int i = 0; i < 2 ; i++) {
StartServer("B");
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}


if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
StartServer("B");
}


StartServer("B");
if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("A");
}



}


}
}
