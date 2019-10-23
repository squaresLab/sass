public class Plan1571771461120 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("A");
}

}

for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}

} else {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

}

StartServer("B");

}

StartServer("A");


}
}
