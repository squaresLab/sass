public class Plan1571767973388 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
if ( DecreaseDimmer("C") ) {
StartServer("B");
} else {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

}

}

DecreaseTraffic("A");
StartServer("C");


}

}
}
