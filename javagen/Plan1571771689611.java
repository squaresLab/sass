public class Plan1571771689611 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}


StartServer("C");
DecreaseTraffic("A");


if ( DecreaseTraffic("A") ) {
StartServer("A");
} else {
StartServer("B");
}

for (int i = 0; i < 3 ; i++) {
StartServer("A");
StartServer("A");
StartServer("C");


StartServer("B");

}



}
}
