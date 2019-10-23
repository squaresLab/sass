public class Plan1571771802332 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
for (int i = 0; i < 3 ; i++) {
StartServer("C");
}


StartServer("A");

for (int i = 0; i < 3 ; i++) {
StartServer("B");
}


StartServer("A");

} else {
StartServer("C");
}

}

}
}
